(ns abstractions)

;; Equivallent de l'interface en Java
(defprotocol UserRepository

  (get-by-id [_ id] "Get User By Id"))

;; Création d'un Fake Repository 
;; DefRecord
(defrecord UserRepositoryFake []
  UserRepository
  (get-by-id [_ id] {:first-name "John" :last-name "Doe" :id id}))

;;
;; deftype vs defprotocol
;;
(defrecord User [fname lname id])

(def u (User. "John" "Doe" 1))

(:fname u)

(defprotocol IPerson
  (get-fname [_] "Get Person name"))

(deftype Person [fname lname]
  IPerson
  (get-fname [_] fname))

(def person (->Person "John" "Doe"))

(get-fname person)

;; Reify
(defn make-person
  "Creates a new person"
  [fname lname]
  (reify IPerson
    (get-fname [_] fname)))

(. (make-person "John" "Doe") get-fname)

;; Interface
(definterface IUser
  (^String fullname []))

(defrecord user [fname lname]
  IUser
  (fullname [this] (str fname " " lname)))

(.fullname (user. "John" "Doe"))






