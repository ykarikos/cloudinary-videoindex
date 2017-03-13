(defproject videoindex "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "2.3.0"]
                 [org.clojure/data.json "0.2.6"]
                 [environ "0.5.0"]
                 [clj-time "0.8.0"]
                 [hiccup "1.0.5"]]
  :main ^:skip-aot videoindex.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
