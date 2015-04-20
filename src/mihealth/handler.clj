(ns mihealth.handler
  (:require [mihealth.db :as midb])
  (:require [compojure.route :as route]
            [compojure.core :refer [GET defroutes]]
            [ring.util.response :refer [resource-response response]]
            [ring.middleware.json :as middleware]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] (str (dissoc (first (midb/find-all)) :_id)))
  (route/not-found "Not Found"))

;(def app
 ; (wrap-defaults app-routes site-defaults))
(def app
  (-> app-routes
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)
      (wrap-defaults api-defaults)))