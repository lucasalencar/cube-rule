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

(pldb/db-rel toast food)
(pldb/db-rel sandwich food)
(pldb/db-rel taco food)
(pldb/db-rel sushi food)
(pldb/db-rel quiche food)
(pldb/db-rel calzone food)
(pldb/db-rel salad food)

(def cube-rule-facts
  (pldb/db
   [toast :pizza]
   [toast :nigiri-sushi]
   [toast :pumpkin-pie-slice]

   [sandwich :lasagna]
   [sandwich :toast]
   [sandwich :quesadilla]

   [taco :hot-dog]
   [taco :sub-sandwich]
   [taco :slice-of-pie]

   [sushi :falafel-wrap]
   [sushi :pigs-in-a-blanket]
   [sushi :enchilada]

   [quiche :cheesecake]
   [quiche :soup]
   [quiche :falafel-pita]
   [quiche :deep-dish-pizza]
   [quiche :salad]
   [quiche :key-lime-pie]


   [calzone :burrito]
   [calzone :corn-dog]
   [calzone :pie]
   [calzone :dumplings]
   [calzone :pop-tarts]
   [calzone :uncrustables]


   [salad :steak]
   [salad :mashed-potatoes]
   [salad :fried-rice]
   [salad :spaghetti]
   [salad :poutine]
   [salad :soup]))

(pldb/with-db cube-rule-facts
  (logic/run* [category food]
    (toast food)))
