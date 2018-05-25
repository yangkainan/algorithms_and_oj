#lang racket


(define (display_tri tri)
  (map displayln tri)
  )

(define (generate_new_list cur_list)
  (generate_tri cur_list 0)
)

(define (generate_tri cur_list index)
  (cond [(= index 0) (append (generate_tri cur_list (+ 1 index))(list 1))]
        [(= index (length cur_list)) (list 1)]
        [else (append (generate_tri cur_list (+ 1 index))
                       (list (+ (list-ref cur_list index) (list-ref cur_list (+ index -1)))))]
  )
)

(define (pascal_tri_iter n iter result)
  (cond [(= n iter) result]
        [else (pascal_tri_iter n (+ iter 1)
                                (append result
                                       (list (generate_new_list (list-ref result (- (length result) 1))))))
        ]
    )
)

(define (pascal_tri n)
  (pascal_tri_iter n 1 (list (list 1))
  )
  )

;(generate_tri (list 1 2 1 ) 0)

(display_tri (pascal_tri 10))
