(ns cube-rule.main
  (:require [clojure.core.logic :as l]))

(defn cube-rule
  [args]
  (l/run* [q]
     (l/== q true)))

(defn -main
  [& args]
  (println "Cube rule has started")
  (println "Result for your query:"
           (cube-rule args)))
