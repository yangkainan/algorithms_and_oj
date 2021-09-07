package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
	"strings"
)

type inter struct  {
	ToInsert []string
	LastInsertPos int
}

func isInterleave(s1 string, s2 string, s3 string) bool {

	if s1 == "" && s2 == "" && s3 == "" {
		return true
	}

	if len(s3) != len(s1) + len(s2) {
		return false
	}

	if s1 == "" {
		return s2 == s3
	}

	if s2 == "" {
		return s1 == s3
	}



	var all = allInterleaves(strings.Split(s1,""), strings.Split(s2,""))

	fmt.Println(all)

	return contains(all, s3)
}


func contains(all []string, tgt string) bool {
	if len(all) == 0 {
		return tgt == ""
	}

	for _, val := range all {
		if val == tgt {
			return true
		}
	}

	return false
}

func allInterleaves (s1 []string, s2 []string) []string{

	var candidate = inter{
		ToInsert : s1,
		LastInsertPos : -1,
	}

	var res = []inter{ candidate }

	for _, e := range s2 {


		var tmp []inter
		for _, cur := range res {
			tmp = append(tmp, interleave(cur, e)...)
		}

		res = tmp

	}

	var r []string

	for _, v := range res {
		r = append(r, strings.Join(v.ToInsert, ""))
	}

	return r

}

func interleave(s inter, c string) []inter {
	var start = s.LastInsertPos + 1
	var end = len(s.ToInsert)
	var res []inter

	for i := start; i <= end; i++ {
		tmp := append(s.ToInsert[:i],c)
		tmp = append(tmp, s.ToInsert[i:]...)

		res = append(res, inter{
			ToInsert : tmp,
			LastInsertPos : i,
		})
	}
	return res
}


func main() {
	tests := map[string]struct {
		input []string
		want  bool
	}{
		// "aabcc, dbbca, aadbbcbcac -> true":       {input: []string{"aabcc","dbbca", "aadbbcbcac"}, want: true},
		// "aabcc, dbbca, aadbbbaccc -> false":       {input: []string{"aabcc","dbbca", "aadbbcbcac"}, want: false},
		// ", , -> true":       {input: []string{"","", ""}, want: true},
		"a, 1, a1-> true":       {input: []string{"a","1", "a1"}, want: true},
	}

	for name, tc := range tests {
		got := isInterleave(tc.input[0],tc.input[1], tc.input[2])
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
