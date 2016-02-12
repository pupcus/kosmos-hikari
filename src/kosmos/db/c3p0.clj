(ns kosmos.db.c3p0
  (:import com.mchange.v2.c3p0.ComboPooledDataSource))

(defn acquire-increment
  ([^ComboPooledDataSource ds] (.getAcquireIncrement ds))
  ([^ComboPooledDataSource ds n]
   (doto ds
     (.setAcquireIncrement n))))

(defn acquire-retry-attempts
  ([^ComboPooledDataSource ds] (.getAcquireRetryAttempts ds))
  ([^ComboPooledDataSource ds n]
   (doto ds
     (.setAcquireRetryAttempts n))))

(defn acquire-retry-delay
  ([^ComboPooledDataSource ds] (.getAcquireRetryDelay ds))
  ([^ComboPooledDataSource ds ms]
   (doto ds
     (.setAcquireRetryDelay ms))))

(defn auto-commit-on-close
  ([^ComboPooledDataSource ds] (.getAutoCommitOnClose ds))
  ([^ComboPooledDataSource ds bool]
   (doto ds
     (.setAutoCommitOnClose bool))))

(defn automatic-test-table
  ([^ComboPooledDataSource ds] (.getAutomaticTestTable ds))
  ([^ComboPooledDataSource ds table-name]
   (doto ds
     (.setAutomaticTestTable table-name))))

(defn break-after-acquire-failure
  ([^ComboPooledDataSource ds] (.getBreakAfterAcquireFailure ds))
  ([^ComboPooledDataSource ds bool]
   (doto ds
     (.setBreakAfterAcquireFailure bool))))

(defn checkout-timeout
  ([^ComboPooledDataSource ds] (.getCheckoutTimeout ds))
  ([^ComboPooledDataSource ds ms]
   (doto ds
     (.setCheckoutTimeout ms))))

(defn idle-connection-test-period
  ([^ComboPooledDataSource ds] (.getIdleConnectionTestPeriod ds))
  ([^ComboPooledDataSource ds secs]
   (doto ds
     (.setIdleConnectionTestPeriod secs))))

(defn max-pool-size
  ([^ComboPooledDataSource ds] (.getMaxPoolSize ds))
  ([^ComboPooledDataSource ds n]
   (doto ds
     (.setMaxPoolSize n))))

(defn max-idle-time
  ([^ComboPooledDataSource ds] (.getMaxIdleTime ds))
  ([^ComboPooledDataSource ds secs]
   (doto ds
     (.setMaxIdleTime secs))))

(defn max-idle-time-excess-connections
  ([^ComboPooledDataSource ds] (.getMaxIdleTimeExcessConnections ds))
  ([^ComboPooledDataSource ds n]
   (doto ds
     (.setMaxIdleTimeExcessConnections n))))

(defn min-pool-size
  ([^ComboPooledDataSource ds] (.getMinPoolSize ds))
  ([^ComboPooledDataSource ds n]
   (doto ds
     (.setMinPoolSize n))))

(defn num-helper-threads
  ([^ComboPooledDataSource ds] (.getNumHelperThreads ds))
  ([^ComboPooledDataSource ds n]
   (doto ds
     (.setNumHelperThreads n))))

(defn preferred-test-query
  ([^ComboPooledDataSource ds] (.getPreferredTestQuery ds))
  ([^ComboPooledDataSource ds sql]
   (doto ds
     (.setPreferredTestQuery sql))))

(defn test-connection-on-checkin
  ([^ComboPooledDataSource ds] (.getTestConnectionOnCheckin ds))
  ([^ComboPooledDataSource ds bool]
   (doto ds
     (.setTestConnectionOnCheckin bool))))

(defn unreturned-connection-timeout
  ([^ComboPooledDataSource ds] (.getUnreturnedConnectionTimeout ds))
  ([^ComboPooledDataSource ds secs]
   (doto ds
     (.setUnreturnedConnectionTimeout secs))))

(defn datasource [{:keys [classname jdbc-url user password pool]}]
  (let [{:keys [acquire-increment
                acquire-retry-attempts
                acquire-retry-delay
                auto-commit-on-close
                automatic-test-table
                break-after-acquire-failure
                checkout-timeout
                idle-connection-test-period
                max-pool-size
                max-idle-time
                max-idle-time-excess-connections
                min-pool-size
                num-helper-threads
                preferred-test-query
                test-connection-on-checkin
                unreturned-connection-timeout]} pool
        ds (doto (com.mchange.v2.c3p0.ComboPooledDataSource.)
             (.setDriverClass classname)
             (.setJdbcUrl jdbc-url)
             (.setUser user)
             (.setPassword password))]
    (cond-> ds
      acquire-increment (acquire-increment acquire-increment)
      acquire-retry-attempts (acquire-retry-attempts acquire-retry-attempts)
      acquire-retry-delay (acquire-retry-delay acquire-retry-delay)
      auto-commit-on-close (auto-commit-on-close auto-commit-on-close)
      automatic-test-table (automatic-test-table automatic-test-table)
      break-after-acquire-failure (break-after-acquire-failure break-after-acquire-failure)
      checkout-timeout (checkout-timeout checkout-timeout)
      idle-connection-test-period (idle-connection-test-period idle-connection-test-period)
      max-pool-size (max-pool-size max-pool-size)
      max-idle-time (max-idle-time max-idle-time)
      max-idle-time-excess-connections (max-idle-time-excess-connections max-idle-time-excess-connections)
      min-pool-size (min-pool-size min-pool-size)
      num-helper-threads (num-helper-threads num-helper-threads)
      preferred-test-query (preferred-test-query preferred-test-query)
      test-connection-on-checkin (test-connection-on-checkin test-connection-on-checkin)
      unreturned-connection-timeout (unreturned-connection-timeout unreturned-connection-timeout))))
