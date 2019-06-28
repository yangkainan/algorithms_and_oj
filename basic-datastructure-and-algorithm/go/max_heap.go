package main

import "fmt"

type MaxHeap struct {
	Capacity int
	elements []int
	currentSize int
}

func CreateMaxHeap( array []int ) *MaxHeap {

	if len(array) > 0 {
		heap := &MaxHeap{
			Capacity: len(array),
			elements:make([]int, len(array)),
			currentSize:0,
		}

		for _, val := range array {
			heap.Insert(val)
		}
		return heap
	}
	return nil
}

func (mH *MaxHeap) Display() []int {
	fmt.Printf("Heap: %v\n", (*mH))
	return mH.elements
}

func (mH *MaxHeap) Insert(element int) {
	if (*mH).currentSize > (*mH).Capacity {
		panic("Exceed the capacity")
	}
	(*mH).elements[(*mH).currentSize] = element
	(*mH).currentSize ++

	mH.bubbleUp()
}
func (mH *MaxHeap) ExtractRoot() int {
	if (*mH).currentSize == 0 {
		panic("Empty")
	}
	root := (*mH).elements[0]


	mH.swap(0, (*mH).currentSize - 1)

	(*mH).currentSize --

	mH.bubbleDown()

	return root
}

func (mH *MaxHeap) swap(i, j int) {

	(*mH).elements[i], (*mH).elements[j] = (*mH).elements[j], (*mH).elements[i]

}

func (mH *MaxHeap) bubbleUp() {

	current := (*mH).currentSize - 1


	for ; current != 0; current = (current /2) {

		if (*mH).elements[current/2] < (*mH).elements[current] {
			mH.swap(current/2, current)
		} else {
			return
		}
	}
}

func (mH *MaxHeap) bubbleDown() {

	size := (*mH).currentSize - 1


	for current := 0; (current * 2 + 1 <= size); {
		left := current * 2 + 1
		right := current * 2 + 2
		leftValue := (*mH).elements[left]
		curValue := (*mH).elements[current]

		swaped := false

		if right <= size {
			rightValue := (*mH).elements[right]

			if curValue < leftValue || curValue < rightValue {
				if leftValue < rightValue {
					mH.swap(current, right)
					current = right
				} else {
					mH.swap(current, left)
					current = left
				}
				swaped = true
			}
		} else {
			if curValue < leftValue {
				mH.swap(current, left)
				current = left
				swaped = true
			}
		}

		if ! swaped {
			return
		}

	}




}


func (mH *MaxHeap) HeapSize() int {
	return (*mH).currentSize
}

