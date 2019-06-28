package main

import (
	"reflect"
	"sort"
	"testing"
)

func TestCreateMaxHeap(t *testing.T) {
	type args struct {
		array []int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		{
			name : "Create Max Heap",
			args: args{[]int{3,2,1,7,8,4,10,16,12}},
			want: []int{16,12,10,8,4,3,2,7,1},

		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			var got *MaxHeap
			if got = CreateMaxHeap(tt.args.array); !reflect.DeepEqual(got.Display(), tt.want) {
				t.Errorf("CreateMaxHeap() = %v, want %v", got.Display(), tt.want)
			}


			sort.Sort(sort.Reverse(sort.IntSlice(tt.args.array)))

			for _, expected := range tt.args.array {
				real := got.ExtractRoot()
				got.Display()
				if !reflect.DeepEqual(real, expected) {
					t.Errorf("Got = %v, want = %v", real, expected)

				}
			}

		})
	}
}

