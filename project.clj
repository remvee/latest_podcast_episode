(defproject latest_podcast_url "0.1.0-SNAPSHOT"
  :main latest-podcast-url.core
  :description "Redirect to latest podcast URL."
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/data.xml "0.0.8"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [ring/ring-core "1.6.3"]])
