(ns kosmos.db
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.string :as str]
            [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [kosmos.db.util :as u]
            [kosmos.db.hikari :as hikari])
  (:import [com.zaxxer.hikari HikariDataSource HikariConfig]))

(defn datasource [{:keys [classname jdbc-url user username password pool] :as component}]
  (let [{:keys [allow-pool-suspension
                auto-commit
                catalog
                connection-init-sql
                connection-test-query
                connection-timeout
                datasource-classname
                datasource-jndi
                datasource-properties
                idle-timeout
                initialization-fail-fast
                isolate-internal-queries
                leak-detection-threshold
                max-lifetime
                maximum-pool-size
                minimum-idle
                pool-name
                read-only
                register-mbeans
                transaction-isolation
                validation-timeout
                ]}                              pool]

    (com.zaxxer.hikari.HikariDataSource.
     (cond-> (com.zaxxer.hikari.HikariConfig.)
       allow-pool-suspension    (hikari/allow-pool-suspension allow-pool-suspension)
       auto-commit              (hikari/auto-commit auto-commit)
       catalog                  (hikari/catalog catalog)
       classname                (hikari/classname classname)
       connection-init-sql      (hikari/connection-init-sql connection-init-sql)
       connection-test-query    (hikari/connection-test-query connection-test-query)
       connection-timeout       (hikari/connection-timeout connection-timeout)
       datasource-classname     (hikari/datasource-classname datasource-classname)
       datasource-jndi          (hikari/datasource-jndi datasource-jndi)
       datasource-properties    (hikari/datasource-properties datasource-properties)
       idle-timeout             (hikari/idle-timeout idle-timeout)
       initialization-fail-fast (hikari/initialization-fail-fast initialization-fail-fast)
       isolate-internal-queries (hikari/isolate-internal-queries isolate-internal-queries)
       jdbc-url                 (hikari/jdbc-url jdbc-url)
       leak-detection-threshold (hikari/leak-detection-threshold leak-detection-threshold)
       max-lifetime             (hikari/max-lifetime max-lifetime)
       maximum-pool-size        (hikari/maximum-pool-size maximum-pool-size)
       minimum-idle             (hikari/minimum-idle minimum-idle)
       password                 (hikari/password password)
       pool-name                (hikari/pool-name pool-name)
       read-only                (hikari/read-only read-only)
       register-mbeans          (hikari/register-mbeans register-mbeans)
       transaction-isolation    (hikari/transaction-isolation transaction-isolation)
       user                     (hikari/username user)
       username                 (hikari/username username)
       validation-timeout       (hikari/validation-timeout validation-timeout)))))

(defrecord DbComponent []
  component/Lifecycle
  (start [{:keys [classname subprotocol subname user username password params pool] :as component}]
    (try
      (log/info "Starting Database component.")
      (let [subname    (or subname (u/build-subname component))
            jdbc-url   (str "jdbc:" subprotocol ":" subname (when params (u/build-query-string component)))
            datasource (when pool (datasource (merge component {:jdbc-url jdbc-url})))]
        (merge component
               {:jdbc-url jdbc-url
                :subname  subname
                :user     (or user username)
                :username (or username user)
                :password password}
               (when datasource
                 {:datasource datasource})))
      (catch Exception e
        (log/error "Database is not running or is not accessible with the current settings: " component)
        (throw e))))

  (stop [{:keys [datasource] :as component}]
    (log/info "Stopping Database component.")
    (when datasource (.close datasource))
    component))
