(ns macros)


(defmacro unless
  "Similar to if but negates the condition"
  [condition & forms]
  `(if (not ~condition)
     ~@forms))

;; quote ``=> return la liste
;; unquote ~ execution du code
;; unquote splicing  ~@ utilisé pour faire des forms  


(comment
  (unless (= 1 2)
          "one does not equal two"
          "one equals two"))



