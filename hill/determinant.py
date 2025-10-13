import numpy as np

# Get input from user
n= int(input("Enter n: "))

m=np.zeros((n,n))

for j in range(n):
    for i in range(n):
        m[i][j]=int(input())

# Compute determinant
det = np.linalg.det(m)

print("\nOriginal Matrix:\n", m)
print("\nDeterminant of the matrix:", det)
