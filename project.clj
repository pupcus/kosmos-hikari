(defproject kosmos-db "0.0.1-SNAPSHOT"

  :description "pre-ordained system components for use with mysql database"

  :url "https://bitbucket.org/pupcus/kosmos-mysql"

  :scm {:url "git@bitbucket.org:bitbucket/kosmos-mysql"}

  :dependencies [[kosmos "0.0.2-SNAPSHOT"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.clojure/java.jdbc "0.4.2"]
                 [c3p0/c3p0 "0.9.1.2"]]

  :profiles {:dev {:dependencies [[org.clojure/clojure "1.8.0"]
                                  [midje "1.9.0-SNAPSHOT"]
                                  [org.hsqldb/hsqldb "2.3.2"]]
                   }
             ;; You can add dependencies that apply to `lein midje` below.
             ;; An example would be changing the logging destination for test runs.
             :midje {}}

  :global-vars {*warn-on-reflection* true
                *assert* false})
