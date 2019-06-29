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

(pldb/db-rel cube-rule starch-sides category)
(pldb/db-rel food-starch-sides starch-sides food)

(def cube-rule-facts
  (pldb/db
   [cube-rule 1 :toast]
   [cube-rule 2 :sandwich]
   [cube-rule 3 :taco]
   [cube-rule 4 :sushi]
   [cube-rule 5 :quiche]
   [cube-rule 6 :calzone]
   [cube-rule 0 :salad]

   [food-starch-sides 1 :pizza]
   [food-starch-sides 1 :nigiri-sushi]
   [food-starch-sides 1 :pumpkin-pie-slice]

   [food-starch-sides 2 :lasagna]
   [food-starch-sides 2 :toast]
   [food-starch-sides 2 :quesadilla]

   [food-starch-sides 3 :hot-dog]
   [food-starch-sides 3 :sub-sandwich]
   [food-starch-sides 3 :slice-of-pie]

   [food-starch-sides 4 :falafel-wrap]
   [food-starch-sides 4 :pigs-in-a-blanket]
   [food-starch-sides 4 :enchilada]

   [food-starch-sides 5 :cheesecake]
   [food-starch-sides 5 :soup-in-a-bread-bowl]
   [food-starch-sides 5 :falafel-pita]
   [food-starch-sides 5 :deep-dish-pizza]
   [food-starch-sides 5 :salad-in-a-bread-bowl]
   [food-starch-sides 5 :key-lime-pie]

   [food-starch-sides 6 :burrito]
   [food-starch-sides 6 :corn-dog]
   [food-starch-sides 6 :pie-whole]
   [food-starch-sides 6 :dumplings]
   [food-starch-sides 6 :pop-tarts]
   [food-starch-sides 6 :uncrustables-unbitten]

   [food-starch-sides 0 :steak]
   [food-starch-sides 0 :mashed-potatoes]
   [food-starch-sides 0 :fried-rice]
   [food-starch-sides 0 :spaghetti]
   [food-starch-sides 0 :poutine]
   [food-starch-sides 0 :soup-a-wet-salad]))

(defn cube-ruleo
  [food category]
  (logic/fresh [sides]
    (cube-rule sides category)
    (food-starch-sides sides food)))

(pldb/with-db cube-rule-facts
  (logic/run* [q]
    (logic/fresh [food]
      (cube-ruleo food :quiche)
      (logic/== q food))))
