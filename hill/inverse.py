import numpy as np

print("Enter n")
n=int(input())

m=np.zeros((n,n))

for j in range(n):
    for i in range(n):
        m[i][j]=int(input())


det = np.linalg.det(m)
if det == 0:
    print("This matrix is not invertible (determinant = 0).")
else:
    inv_matrix = np.linalg.inv(m)
    print("\nOriginal Matrix:\n", m)
    print("\nInverse Matrix:\n", inv_matrix)