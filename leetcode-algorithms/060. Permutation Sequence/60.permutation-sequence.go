package main

import (
	"fmt"
	"math"
	"sort"
	"strconv"
	"strings"

	"github.com/google/go-cmp/cmp"
)

type MinHeap []string

func (a MinHeap) Len() int      { return len(a) }
func (a MinHeap) Swap(i, j int) { a[i], a[j] = a[j], a[i] }
func (a MinHeap) Less(i, j int) bool {

	var left, _ = strconv.ParseInt(a[i], 10, 32)
	var right, _ = strconv.ParseInt(a[j], 10, 32)
	return left < right
}
func (h *MinHeap) Push(x interface{}) {
	// Push and Pop use pointer receivers because they modify the slice's length,
	// not just its contents.
	*h = append(*h, x.(string))
}

func (h *MinHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func getPermutation(n int, k int) string {
	if n < 1 || n > 9 {
		return ""
	}

	if k < 1 || k > factorial(n) {
		return ""
	}

	var nums = make([]string, n)

	var cpN = n
	for index := range nums {
		nums[index] = strconv.Itoa(cpN)
		cpN--
	}

	var toSortedResult = permutation(nums)

	var orderMap = make(map[string][]string)

	for _, element := range toSortedResult {
		var key = strings.Split(element, "")[0]

		arry, ok := orderMap[key]
		if !ok {
			orderMap[key] = []string{element}
		} else {
			orderMap[key] = append(arry, element)
		}
	}

	var m = len(toSortedResult) / n
	var index = int(math.Ceil(float64(k) / float64(m)))
	var offset = k - ((index - 1) * m)

	// fmt.Printf("Len=%v, m=%v, k=%v, index=%v, offset=%v, perm=%v\n",
	// 	len(toSortedResult), m, k, index, offset, toSortedResult)

	var minHeap = MinHeap(orderMap[strconv.Itoa(index)])

	// heap.Init(&minHeap)

	// for i := 1; i < offset; i++ {
	// 	heap.Pop(&minHeap)
	// }

	// return heap.Pop(&minHeap).(string)

	sort.Sort(minHeap)
	return minHeap[offset-1]
}

func permutation(nums []string) []string {

	if nums == nil || len(nums) == 0 {
		return nil
	}
	if len(nums) == 1 {
		return nums
	}

	var pivot = nums[0]

	var subPermutation = permutation(nums[1:])

	var result = addCharToPermutations(pivot, subPermutation)

	return result

}

func addCharToPermutations(one string, perm []string) []string {

	var result []string

	for _, elem := range perm {
		result = append(result, addCharToPermutation(one, elem)...)
	}

	return result

}

func addCharToPermutation(one string, two string) []string {

	var workingArea = strings.Split(two, "")

	var result []string

	for index := range workingArea {
		// fmt.Printf("index:%v, %v, %v, %v \n", index, workingArea[:index], one, workingArea[index:])
		var tmp = append([]string{}, workingArea[:index]...)
		tmp = append(tmp, one)
		tmp = append(tmp, workingArea[index:]...)

		result = append(result, strings.Join(tmp, ""))
	}

	result = append(result, strings.Join(append(workingArea, one), ""))

	return result
}

func factorial(x int) (result int) {
	if x == 0 {
		result = 1
	} else {
		result = x * factorial(x-1)
	}
	return
}

func main() {

	tests := map[string]struct {
		input []int
		want  string
	}{
		"n=1,k=1":        {input: []int{1, 1}, want: "1"},
		"n=2,k=2":        {input: []int{2, 2}, want: "21"},
		"n=3,k=3":        {input: []int{3, 3}, want: "213"},
		"n=4,k=9":        {input: []int{4, 9}, want: "2314"},
		"n=9,k=9":        {input: []int{9, 9}, want: "123457869"},
		"n out of range": {input: []int{10, 3}, want: ""},
		"k out of range": {input: []int{3, 7}, want: ""},
	}

	for name, tc := range tests {
		got := getPermutation(tc.input[0], tc.input[1])
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
