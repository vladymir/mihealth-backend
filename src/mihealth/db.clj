(ns mihealth.db
    (:require [monger.core :as mg]
              [monger.collection :as mc]
              [monger.conversion :refer [from-db-object]]))

(def database (atom nil))

(defn init [] (reset! database 
    (mg/connect-via-uri "mongodb://localhost/mihealth")))

(def mihealthdb (:db @database))
(def mihealthconn (:conn @database))

(defn disconnect [] (reset! database (mg/disconnect (:conn @database))))

(defn find-all [] (mc/find-maps (:db @database) "users"))


(defn sum-all ([coll] (sum-all coll 0)) 
              ([coll sum] (if (empty? coll) sum
                (recur (rest coll) (+ sum (first coll) )))))