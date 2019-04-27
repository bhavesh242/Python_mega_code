def string_length(mystring):
    if type(mystring) == int:
        return "sorry, we don't serve Integers"
    elif type(mystring) == float :
        return "Sorry, we dont serve floats"
    else :
        return len(mystring)

print(string_length("Bhavesh"))
print(string_length(10))
print(string_length(2.42))
