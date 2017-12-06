(ns latest-podcast-url.core
  (:gen-class)
  (:require [clojure.data.xml :as xml]
            [ring.adapter.jetty :refer [run-jetty]]))

(def url "http://radiobox2.omroep.nl/rss/ug_itunes/programme/35.rss")

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

(defn app [request]
  (let [media-url (-> url podcast-data latest-url)]
    {:status  302 ;; Found
     :headers {"Location" media-url}}))

(defn -main []
  (let [host (get (System/getenv) "HOST")
        port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (run-jetty #'app {:host host :port port})))
