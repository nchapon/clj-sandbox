(ns macros-test 
  (:require
    [clojure.test :refer [deftest testing is]]
    [macros :as SUT]))

(deftest test-unless-macro
  (testing
   "Testing unless macro"
    (is (true?
           (SUT/unless (= 1 2) true false)))
    (is (false?
           (SUT/unless (= 1 1) true false)))))





