(defproject latest_podcast_episode "0.1.0-SNAPSHOT"
  :main latest-podcast-episode.core
  :description "Redirect to latest podcast episode."
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/data.xml "0.0.8"]
                 [ring/ring-jetty-adapter "1.7.0"]
                 [ring/ring-core "1.7.0"]]

  :uberjar-name "latest_podcast_episode.jar"
  :profiles {:uberjar {:aot :all}})
