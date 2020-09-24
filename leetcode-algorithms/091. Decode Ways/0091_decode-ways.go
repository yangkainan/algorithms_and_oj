package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
	"strings"
)

/*

A message containing letters from A-Z is being encoded to numbers using the following mapping:


'A' -> 1
'B' -> 2
...
'Z' -> 26

Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:


Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).

Example 2:


Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
*/


func numDecodings(s string) int {


	var res = decode(strings.Split(s, ""), 0)

	res = removeInvalid(res)

	return len(res)
}

func removeInvalid(s [][]string) [][]string {

 	var codeMape = map[string]string{
 		"1" : "A",
		"2" : "A",
		"3" : "A",
		"4" : "A",
		"5" : "A",
		"6" : "A",
		"7" : "A",
		"8" : "A",
		"9" : "A",
		"10" : "A",
		"11" : "A",
		"12" : "A",
		"13" : "A",
		"14" : "A",
		"15" : "A",
		"16" : "A",
		"17" : "A",
		"18" : "A",
		"19" : "A",
		"20" : "A",
		"21" : "A",
		"22" : "A",
		"23" : "A",
		"24" : "A",
		"25" : "A",
		"26" : "A",

	}


	var res = [][]string{}

	for i := 0; i < len(s); i++ {

		var allValid = true
		for _, val := range s[i] {

			if _, ok := codeMape[val]; !ok {
				allValid = false
				break
			}

		}
		if allValid {
			res = append(res, s[i])
		}

	}

	return res


}

func decode(s []string, index int) [][]string {
	if index >= len(s) {
		return [][]string{}
	}

	var inter = decode(s, index + 1)

	if len(inter) == 0 {
		return [][]string{{s[index]}}
	}

	var res [][]string
	for i := 0; i < len(inter); i++ {
		var cur = inter[i]

		res = append(res, append([]string{s[index]}, cur... ))

		if len(cur[0]) <= 1 {
			var tmp = s[index] + cur[0]
			res = append(res, append([]string{tmp}, cur[1:]...))
		}

	}



	return res
}


func main() {
	tests := map[string]struct {
		input string
		want int
	}{
		"0, 0":       {input: "0", want: 0},
		"01, 0":       {input: "01", want: 0},
		"100, 0":       {input: "0", want: 0},
		"12, 2":       {input: "12", want: 2},
		"226, 3":       {input: "226", want: 3},
	}

	for name, tc := range tests {
		got := numDecodings(tc.input)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
