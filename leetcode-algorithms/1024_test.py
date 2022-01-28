

def solve(val_list, cur_res, op_list):
    if cur_res == 1024 and len(val_list) == 0:
        print(op_list)
        return True

    if len(val_list) == 0:
        return False

    for val in val_list:
        new_val_list = val_list.copy()
        new_val_list.remove(val)
        for op in ["+", "-", "*", "/"]:
            new_res = cur_res
            new_op_list = op_list.copy()
            if op == "+":
                new_res += val
            elif op == "-":
                new_res -= val
            elif op == "*":
                new_res = new_res * val
            elif op == "/":
                new_res = new_res / val
            else:
                print("error")
                return False
            new_op_list.append(op)
            new_op_list.append(val)
            if solve(new_val_list, new_res, new_op_list):
                return True

    return False



if __name__ == '__main__':
    val_list = [200, 404, 406, 414]
    solve(val_list, 0, [])

    val_list = [423, 600, 100, 100]
    solve(val_list, 0, [])

    val_list = [202, 101, 412, 200]
    solve(val_list, 0, [])

