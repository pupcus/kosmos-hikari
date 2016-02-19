(defproject kosmos/kosmos-hikari "0.0.1-SNAPSHOT"

  :description "simple jdbc database component with hikari connection pooling"

  :url "https://bitbucket.org/pupcus/kosmos-hikari"

  :scm {:url "git@bitbucket.org:bitbucket/kosmos-hikari"}

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[kosmos "0.0.2-SNAPSHOT"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.clojure/java.jdbc "0.4.2"]
                 [com.zaxxer/HikariCP "2.4.3"]]

  :profiles {:dev {:resource-paths ["dev-resources"]
                   :dependencies   [[org.clojure/clojure "1.8.0"]
                                    [midje "1.9.0-SNAPSHOT"]
                                    [org.hsqldb/hsqldb "2.3.3"]
                                    [org.slf4j/slf4j-log4j12 "1.7.5"]]}
             ;; You can add dependencies that apply to `lein midje` below.
             ;; An example would be changing the logging destination for test runs.
             :midje {}})
