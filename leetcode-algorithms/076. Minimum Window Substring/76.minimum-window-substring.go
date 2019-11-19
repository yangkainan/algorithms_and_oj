//package main
//
//import (
//	"fmt"
//	"github.com/google/go-cmp/cmp"
//	"strings"
//)

/*

给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。

示例：

输入: S = "ADOBECODEBANC", T = "ABC"
输出: "BANC"

说明：
	如果 S 中不存这样的子串，则返回空字符串 ""。
	如果 S 中存在这样的子串，我们保证它是唯一的答案
*/

func minWindow(s string, t string) string {

	if s == "" || t == "" {
		return ""
	}

	if len(s) < len(t) {
		return ""
	}

	needToFindCharMap := makeHash(t)
	srcCharMap := makeHash(s)
	if !contains(&srcCharMap, &needToFindCharMap) {
		return ""
	}

	startIndex := 0

	for ; startIndex < len(s); startIndex++ {
		tmp := s[startIndex : startIndex+1]
		if _, ok := needToFindCharMap[tmp]; ok {
			break
		}
	}

	if startIndex >= len(s) {
		return ""
	}

	endIndex := startIndex + len(t)

	if endIndex > len(s) {
		return ""
	}

	srcCharMap = makeHash(s[startIndex:endIndex])

	minus(&needToFindCharMap, &srcCharMap)

	minResult := ""
	if isEmpty(&needToFindCharMap) {
		minResult = s[startIndex:endIndex]
	}

	for endIndex ++; endIndex <= len(s); endIndex++ {
		// window increase

		tmp := s[endIndex - 1 : endIndex]
		decrease(&needToFindCharMap, tmp)

		if isEmpty(&needToFindCharMap) {
			if minResult == "" || len(minResult) > (endIndex-startIndex) {
				minResult = s[startIndex:endIndex]
			}
			// window decrease
			for startIndex ++ ; startIndex <= endIndex ; startIndex ++ {
				tmp = s[startIndex - 1: startIndex]
				increase(&needToFindCharMap, tmp)

				if isEmpty(&needToFindCharMap) {
					if minResult == "" || len(minResult) > (endIndex-startIndex) {
						minResult = s[startIndex :endIndex]
					}

				} else {
					break
				}
			}
		}

	}

	return minResult

}

func makeHash(t string) map[string]int {
	arr := strings.Split(t, "")

	result := make(map[string]int)

	for _, char := range arr {
		if cnt, ok := result[char]; ok {
			result[char] = cnt + 1
		} else {
			result[char] = 1
		}
	}
	return result
}

func isEmpty(s *map[string]int) bool {
	allFound := true

	for _, val := range (*s) {
		if val > 0 {
			allFound = false
			break
		}
	}

	return allFound
}

func decrease(s *map[string]int, key string) {

	if cnt, ok := (*s)[key]; ok {
		(*s)[key] = cnt - 1
	}

}

func increase(s *map[string]int, key string) {
	if cnt, ok := (*s)[key]; ok {
		(*s)[key] = cnt + 1
	}
}

func contains(s, t *map[string]int) bool {
	for key, tCnt := range *t {
		if sCnt, ok := (*s)[key]; ok {
			if tCnt > sCnt {
				return false
			}

		} else {
			return false
		}
	}

	return true
}

func minus(s, t *map[string]int) {
	for key, sCnt := range *s {
		if tCnt, ok := (*t)[key]; ok {
			newCnt := sCnt - tCnt
			(*s)[key] = newCnt
		}
	}
}

//func main() {
//	tests := map[string]struct {
//		input struct {
//			s string
//			t string
//		}
//		want string
//	}{
//		"simple": {input: struct {
//							s string
//							t string }{s: "ADOBECODEBANC", t: "ABC"},
//				   want: "BANC"},
//		"second": {input: struct {
//			s string
//			t string }{s: "a", t: "aa"},
//			want: ""},
//
//		"3rd": {input: struct {
//			s string
//			t string }{s: "bdab", t: "ab"},
//			want: "ab"},
//
//	}
//
//	for name, tc := range tests {
//		got := minWindow(tc.input.s, tc.input.t)
//		diff := cmp.Diff(tc.want, got)
//		if diff != "" {
//			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
//				name, tc.input, tc.want, got, diff)
//		}
//
//	}
//
//}
