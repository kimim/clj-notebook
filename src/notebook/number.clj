(ns notebook.number
  (:require
   [notespace.api]
   [notespace.kinds :as kinds]))

["# Clojure的数值类型"]

["Clojure的数值类型有以下几种：
- 整数
- 浮点数
- 分数"]

["## 整数"]

["Clojure的整数其实就是Java的Long类型："]
(type 0)
;; => java.lang.Long

(+ 0 1)
;; => 1
(- 0 1)
;; => -1
(* 0 1)
;; => 0
["0是不能做除数的，会产生Exception："]
(try
  (/ 1 0)
  (catch  Exception e (.getMessage e)))
;; => "Divide by zero"

["两个整数不能整除的时候，结果是分数："]

(/ 1 2)
;; => 1/2

["## 浮点数"]

["Clojure的浮点数其实就是Java的Double类型："]
(type 0.)
;; => java.lang.Double

(+ 0. 1.)
;; => 1.0
(- 0. 1.)
;; => -1.0
(* 0. 1.)
;; => 0.0
(try
  (/ 1. 0.)
  (catch Exception e (.getMessage e)))
;; => "Divide by zero"

(/ 1. 2.)
;; => 0.5
(/ 1. 2)
;; => 0.5

["# 数值相关的函数"]

["## 大小关系判断"]

["### = 和 =="]

(== 0 0.)
;; => true
(= 0 0.)
;; => false
(== 1 1.)
;; => true
(= 1 1.)
;; => false
(== 2 2.)
;; => true
(= 2 2.)
;; => false

(== 2 2)
;; => true
(= 2 2)
;; => true

["从上面的例子可以看出，Clojure中`==`是对数值的相等判断，不关心是整数还是浮点。
而`=`还会判断是否类型也相同。并且`=`是一个更通用的相等判断函数，不仅能用于数值，
还能用于其他类型。而`==`只能用于数值的相等判断。"]
