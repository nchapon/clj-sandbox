(ns exceptions
  (:require
   [clojure.spec.alpha :as s]))

;; Examples
(try
  (/ 1 0)
  (catch ArithmeticException e
    (println "Cannot divide by zero !")))

(try
  (/ 1 0)
  (+ 1 nil)
  (catch Throwable e
    (println "Catch everything")))

(defn add [a b]
  (if (and a b)
    (+ a b)
    (let [message (format "Value error, a: %s, b: %s" a b)]
      (throw (new Exception message)))))

;; ExceptionInfo

(defn add-2 [a b]
  (if (and a b)
    (+ a b)
    (throw (ex-info
            "Cannot add these two numbers."
            {:a a
             :b b}))))

(try
  (add-2 1 nil)
  (catch clojure.lang.ExceptionInfo e
    (let [{:keys [a b]} (ex-data e)]
      (format "Value error, a: %s, b: %s" a b))))

;; Throw exception wit clojure.specs 
(let [{:keys [a b c]} nil]
  [a b c])

(s/def ::data  (s/coll-of int?))

(when-let [explain (s/explain-data ::data [1 2 nil])]
  (throw (ex-info "Some item is not integer"
                  {:explain explain})))

;; Chaining Exceptions

(defn divide
  "Divide two numbers"
  [a b]
  (try
    (/ a b)
    (catch ArithmeticException e
      (ex-info "Calculation error"
               {:a a :b b}
               e))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Pattern protected call from LUA
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn pcall [f & args]
  (try
    [true (apply f args)]
    (catch Exception e [false e])))

;; Should print failure
(let [[ok? result-error] (pcall inc "a")]
  (if ok?
    result-error
    (println "Failure")))




