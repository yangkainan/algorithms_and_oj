//package main

func mySqrt(x int) int {


	if x < 1 {
		return 0
	}


	result := 1

	for i:=1; i <= (x/2); i++ {
		tmp:= i *i

		if tmp == x {
			return i
		}

		if tmp < x {
			result = i
		} else {
			break
		}


	}




	return result

}

//func main() {
//	tests := map[string]struct {
//		input int
//		want  int
//	}{
//		"8 -> 2":       {input: 8, want: 2},
//		"4 -> 2":    {input: 4, want: 2},
//		"1 -> 1":       {input: 1, want: 1},
//		"0 -> 0":       {input: 0, want: 0},
//	}
//
//	for name, tc := range tests {
//		got := mySqrt(tc.input)
//		diff := cmp.Diff(tc.want, got)
//		if diff != "" {
//			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
//				name, tc.input, tc.want, got, diff)
//		}
//
//	}
//
//}
