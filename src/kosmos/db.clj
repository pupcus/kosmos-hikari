(ns kosmos.db
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.string :as str]
            [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [kosmos.db.c3p0 :as c3p0]))

(defn query-string [params]
  (when (seq params)
    (str/join "&" (map (fn [[k v]] (str (name k) "=" v)) params))))

(defrecord DbComponent []

  component/Lifecycle

  (start [{:keys [classname subprotocol subname host port database user password params]
           :or {host "localhost" port 3306}
           :as component}]
    (try
      (log/info "Starting Database component.")
      (let [subname  (or subname (str "//" host (when port (str ":" port)) "/" database))
            jdbc-url (str "jdbc:" subprotocol ":" subname (when (seq params) (str "?" (query-string params))))]
        (merge
         component
         {:datasource (c3p0/datasource (merge component {:classname classname :jdbc-url jdbc-url :user user :password password}))
          :classname classname
          :subprotocol subprotocol
          :subname subname
          :jdbc-url jdbc-url
          :user user
          :password password
          :database database}))
      (catch Exception e
        (log/error "Database is not running or is not accessible with the current settings: "
                   component)
        (throw e))))

  (stop [component]
    (log/info "Stopping Database component.")
    (when-let [pool (:datasource component)]
      (.close ^com.mchange.v2.c3p0.ComboPooledDataSource pool))
    component))
