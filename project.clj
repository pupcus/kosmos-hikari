(defproject kosmos/kosmos-hikari "0.0.5"

  :description "simple jdbc database component with hikari connection pooling"

  :url "https://bitbucket.org/pupcus/kosmos-hikari"

  :scm {:url "git@bitbucket.org:pupcus/kosmos-hikari"}

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[kosmos "0.0.10"]
                 [org.clojure/tools.logging "0.5.0"]
                 [org.clojure/java.jdbc "0.7.10"]
                 [com.zaxxer/HikariCP "3.4.1"]]

  :profiles {:dev {:resource-paths ["dev-resources"]
                   :dependencies   [[org.clojure/clojure "1.10.1"]
                                    [kosmos/kosmos-hsqldb-server "0.0.4"]
                                    [org.slf4j/slf4j-log4j12 "1.7.25"]]}}

  :deploy-repositories {"releases" {:url "https://repo.clojars.org" :creds :gpg :sign-releases false}
                        "snapshots" {:url "https://repo.clojars.org" :creds :gpg :sign-releases false}}

  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag" "--no-sign"]
                  ["deploy"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]]

  :global-vars {*warn-on-reflection* true
                *assert* false})
