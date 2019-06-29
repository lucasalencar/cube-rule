(ns cube-rule.core
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :as logic]
            [clojure.core.logic.pldb :as pldb]))

(pldb/db-rel eatable eatable-thing living-being)

(def eatable-facts
  (pldb/db
   [eatable :burrito :human]
   [eatable :pizza :human]
   [eatable :nigiri-sushi :human]

   [eatable :dog-food :dog]

   [eatable :cat-food :cat]
   [eatable :fish :cat]))

(pldb/with-db eatable-facts
  (logic/run* [eatable-thing]
    (logic/fresh [living-being]
      (logic/== living-being :human)
      (eatable eatable-thing living-being))))
