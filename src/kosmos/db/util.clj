(ns kosmos.db.util
  (:require [clojure.string :as str]))

(defn contains [s subs]
  (when (and s subs)
    (.contains s subs)))

(defn query-string [sep params]
  (when (seq params)
    (str/join sep (map (fn [[k v]] (str (name k) "=" v)) params))))

(defmulti build-query-string :subprotocol)

(defmethod build-query-string "hsqldb" [{:keys [params]}]
  (str ";" (query-string ";" params)))

(defmethod build-query-string :default [{:keys [subname params]}]
  (str (if-not (contains subname "?") "?" "&") (query-string "&" params)))



(defn subname* [{:keys [host port database]}]
  (str "//" host (when port (str ":" port)) "/" database))

(defmulti build-subname :subprotocol)

(defmethod build-subname "hsqldb" [{:keys [protocol database params] :as component}]
  (if (#{"file" "mem"} protocol)
    (str protocol "/" database)
    (str protocol ":" (subname* component))))

(defmethod build-subname :default [{:keys [params] :as component}]
  (str (subname* component)))

