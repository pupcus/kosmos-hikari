(ns kosmos.db.hikari
  (:import [com.zaxxer.hikari HikariConfig]))

(defn allow-pool-suspension
  ([^HikariConfig config] (.isAllowPoolSuspension config))
  ([^HikariConfig config b]
   (doto config
     (.setAllowPoolSuspension b))))

(defn auto-commit
  ([^HikariConfig config] (.isAutoCommit config))
  ([^HikariConfig config b]
   (doto config
     (.setAutoCommit b))))

(defn catalog
  ([^HikariConfig config] (.getCatalog config))
  ([^HikariConfig config s]
   (doto config
     (.setCatalog s))))

(defn classname
  ([^HikariConfig config] (.getDriverClassName config))
  ([^HikariConfig config s]
   (doto config
     (.setDriverClassName s))))

(defn connection-init-sql
  ([^HikariConfig config] (.getConnectionInitSql config))
  ([^HikariConfig config s]
   (doto config
     (.setConnectionInitSql s))))

(defn connection-test-query
  ([^HikariConfig config] (.getConnectionTestQuery config))
  ([^HikariConfig config s]
   (doto config
     (.setConnectionTestQuery s))))

(defn connection-timeout
  ([^HikariConfig config] (.getConnectionTimeout config))
  ([^HikariConfig config ms]
   (doto config
     (.setConnectionTimeout ms))))

(defn datasource-classname
  ([^HikariConfig config] (.getDataSourceClassName config))
  ([^HikariConfig config s]
   (doto config
     (.setDataSourceClassName s))))

(defn datasource-jndi
  ([^HikariConfig config] (.getDataSourceJNDI config))
  ([^HikariConfig config s]
   (doto config
     (.setDataSourceJNDI s))))

(defn jdbc-url
  ([^HikariConfig config] (.getJdbcUrl config))
  ([^HikariConfig config s]
   (doto config
     (.setJdbcUrl s))))

(defn idle-timeout
  ([^HikariConfig config] (.getIdleTimeout config))
  ([^HikariConfig config ms]
   (doto config
     (.setIdleTimeout ms))))

(defn initialization-fail-fast
  ([^HikariConfig config] (.isInitializationFailFast config))
  ([^HikariConfig config b]
   (doto config
     (.setInitializationFailFast b))))

(defn isolate-internal-queries
  ([^HikariConfig config] (.isIsolateInternalQueries config))
  ([^HikariConfig config b]
   (doto config
     (.setIsolateInternalQueries b))))

(defn leak-detection-threshold
  ([^HikariConfig config] (.getLeakDetectionThreshold config))
  ([^HikariConfig config ms]
   (doto config
     (.setLeakDetectionThreshold ms))))

(defn max-lifetime
  ([^HikariConfig config] (.getMaxLifetime config))
  ([^HikariConfig config ms]
   (doto config
     (.setMaxLifetime ms))))

(defn maximum-pool-size
  ([^HikariConfig config] (.getMaximumPoolSize config))
  ([^HikariConfig config n]
   (doto config
     (.setMaximumPoolSize n))))

(defn minimum-idle
  ([^HikariConfig config] (.getMinimumIdle config))
  ([^HikariConfig config n]
   (doto config
     (.setMinimumIdle n))))

(defn password
  ([^HikariConfig config] (.getPassword config))
  ([^HikariConfig config s]
   (doto config
     (.setPassword s))))

(defn pool-name
  ([^HikariConfig config] (.getPoolName config))
  ([^HikariConfig config s]
   (doto config
     (.setPoolName s))))

(defn read-only
  ([^HikariConfig config] (.isReadOnly config))
  ([^HikariConfig config b]
   (doto config
     (.setReadOnly b))))

(defn register-mbeans
  ([^HikariConfig config] (.isRegisterMbeans config))
  ([^HikariConfig config b]
   (doto config
     (.setRegisterMbeans b))))

(defn transaction-isolation
  ([^HikariConfig config] (.getTransactionIsolation config))
  ([^HikariConfig config s]
   (doto config
     (.setTransactionIsolation s))))

(defn user
  ([^HikariConfig config] (.getUsername config))
  ([^HikariConfig config s]
   (doto config
     (.setUsername s))))

(defn username
  ([^HikariConfig config] (.getUsername config))
  ([^HikariConfig config s]
   (doto config
     (.setUsername s))))

(defn validation-timeout
  ([^HikariConfig config] (.getValidationTimeout config))
  ([^HikariConfig config ms]
   (doto config
     (.setValidationTimeout ms))))


(defn datasource-properties
  ([^HikariConfig config] (.getDataSourceProperties config))
  ([^HikariConfig config m]
   (let [props (doto (java.util.Properties.)
                 (.putAll m))]
     (doto config
       (.setDataSourceProperties props)))))
