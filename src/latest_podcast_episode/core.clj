(ns latest-podcast-episode.core
  (:gen-class)
  (:require [clojure.data.xml :as xml]
            [ring.adapter.jetty :refer [run-jetty]]))

(defn podcast-data [url]
  (xml/parse-str (slurp url)))

(defn latest-url [data]
  (->> data
       :content
       first
       :content
       (filter #(= :item (:tag %)))
       first
       :content
       (filter #(= :enclosure (:tag %)))
       first
       :attrs
       :url))

(defn url-decode [url]
  (java.net.URLDecoder/decode url "UTF-8"))

(defn app [{:keys [query-string] :as req}]
  (let [url (->> query-string
                 (drop-while (partial = \/))
                 (apply str)
                 url-decode
                 podcast-data
                 latest-url)]
    {:status  302 ;; Found
     :headers {"Location" url}}))

(defn -main []
  (let [host (get (System/getenv) "HOST")
        port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (run-jetty #'app {:host  host
                      :port  port
                      :join? false})))
