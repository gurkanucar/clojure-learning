(ns first.core  (:require [org.httpkit.server :as server]
                          [compojure.core :refer :all]
                          [compojure.route :as route]
                          [ring.middleware.defaults :refer :all]
                          [clojure.pprint :as pp]
                          [clojure.string :as str]
                          [clojure.data.json :as json])
  (:gen-class))


(defn simple-body-page [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Hello World"})


(defn request-example [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->>
              (pp/pprint req)
              (str "Request Object: " req))})


(defn hello-name [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->
              (pp/pprint req)
              (str "Hello " (:name (:params req))))})

(defroutes app-routes
           (GET "/" [] simple-body-page)
           (GET "/request" [] request-example)
           (GET "/hello" [] hello-name)
           (route/not-found "Error, page not found!"))





(defn -main
  "This is our main entry point"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8080"))]
    ; Run the server with Ring.defaults middleware
    (server/run-server (wrap-defaults #'app-routes site-defaults) {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))



(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(foo "gurkan")
(println (+ 3 5))