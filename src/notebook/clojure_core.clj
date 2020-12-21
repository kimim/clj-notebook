(ns notebook.clojure-core
  (:require
   [notespace.api]
   [notespace.kinds :as kinds]))

["# Namespace"]

["clojure.core 是 clojure 语言的核心 namespace(ns)，包含了最基本的变量和函数。
clojure 通过名字空间 namespace 来对一组相似的行为和状态。"]

["当前的 namespace 可以通过状态变量 `*ns*` 获得："]

*ns*
;; => #namespace[notebook.clojure-core]

(type *ns*)
;; => clojure.lang.Namespace

["`ns-name` 函数获取 namespace 的符号名称，`the-ns` 获取 Namespace 对象："]

(ns-name *ns*)
;; => notebook.clojure-core
(ns-name 'clojure.core)
;; => clojure.core

(the-ns *ns*)
;; => #namespace[notebook.clojure-core]
(the-ns 'clojure.core)
;; => #namespace[clojure.core]

["`all-ns` 返回当前加载的所有 ns，我们在此处通过 `take` 展示前 5 个 ns："]

(->> (all-ns)
     (take 5))
;; => (#namespace[clojure.tools.analyzer.passes.constant-lifter] #namespace[clojure.tools.analyzer.passes.jvm.validate-loop-locals] #namespace[clojure.test] #namespace[cider.nrepl.inlined-deps.toolsreader.v1v3v2.clojure.tools.reader] #namespace[clojure.core.server])

["`ns-map` 返回某个 ns 中所有的符号映射。通过以下比较可以发现，在当前的 ns 中已
经默认包含了 clojure.core 中的符号，以及 java.lang 中的一些类。"]

(take 5 (ns-map *ns*))
;; => ([primitives-classnames #'clojure.core/primitives-classnames] [+' #'clojure.core/+'] [Enum java.lang.Enum] [decimal? #'clojure.core/decimal?] [restart-agent #'clojure.core/restart-agent])

(take 5 (ns-map 'clojure.core))
;; => ([Type clojure.asm.Type] [primitives-classnames #'clojure.core/primitives-classnames] [+' #'clojure.core/+'] [Enum java.lang.Enum] [decimal? #'clojure.core/decimal?])

["clojure.core 中一共有 899 个符号映射。这些映射有分为 public、internal、import、refer 和 alias。"]

(count (ns-map 'clojure.core))
;; => 899

(count (ns-publics 'clojure.core))
;; => 659

(count (ns-interns 'clojure.core))
;; => 773

(count (ns-imports 'clojure.core))
;; => 126

(take 5 (ns-imports 'clojure.core))
;; => ([Type clojure.asm.Type] [Enum java.lang.Enum] [RT clojure.lang.RT] [InternalError java.lang.InternalError] [NullPointerException java.lang.NullPointerException])

(ns-refers 'clojure.core)
;; => {}

(ns-aliases 'clojure.core)
;; => {jio #namespace[clojure.java.io]}

["public 是 intern 的子集："]

(clojure.set/subset?
 (set (keys (ns-publics 'clojure.core)))
 (set (keys (ns-interns 'clojure.core))))

["然而，`ns-map` 的结果里是不包括 `ns-aliases` 的："]

((ns-map 'clojure.core) 'jio)
;; => nil
