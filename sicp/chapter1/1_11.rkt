#lang racket

(define (recursive_f n)
        (cond ((< n 3) n)
              (else (+ (recursive_f (- n 1))
                       (* 2 (recursive_f (- n 2)))
                       (* 3 (recursive_f (- n 3)))
                    )
              )
        )
)

(define (iterate_f n_1 n_2 n_3 iter stop)
        (define new_n1 (+ n_1 (* 2 n_2) (* 3 n_3)))
        (cond ((= iter stop) new_n1)
              (else (iterate_f new_n1
                                n_1
                                n_2
                                (+ iter 1)
                                stop
                    )
                )
        )
)

(define (iterative_f n)
       
  (cond ((< n 3) n)
        (else (iterate_f 2 1 0 3 n))
  )
       
)

(define n 20)

(recursive_f n)
(iterative_f n)