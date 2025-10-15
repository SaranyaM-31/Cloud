# Simple ECC key exchange + encryption in Python

p = 17
a = 2
b = 2

# Point addition
def add(P, Q):
    if P == "INF":
        return Q
    if Q == "INF":
        return P
    
    if P == Q:
        lam = ((3 * P[0]**2 + a) * pow(2 * P[1], -1, p)) % p
    else:
        lam = ((Q[1] - P[1]) * pow(Q[0] - P[0], -1, p)) % p
    
    xr = (lam**2 - P[0] - Q[0]) % p
    yr = (lam * (P[0] - xr) - P[1]) % p
    return (xr, yr)

# Scalar multiplication
def multiply(P, k):
    R = "INF"
    Q = P
    while k > 0:
        if k % 2 == 1:
            R = add(R, Q)
        Q = add(Q, Q)
        k //= 2
    return R

# Base point
G = (5, 1)

# Private keys
alicePrivate = int(input("Enter Alice private key (1-16): "))
bobPrivate = int(input("Enter Bob private key (1-16): "))

# Public keys
alicePublic = multiply(G, alicePrivate)
bobPublic = multiply(G, bobPrivate)

print(f"Alice Public Key: {alicePublic}")
print(f"Bob Public Key: {bobPublic}")

# Shared secret
aliceSecret = multiply(bobPublic, alicePrivate)
bobSecret = multiply(alicePublic, bobPrivate)

print(f"Shared secret (Alice): {aliceSecret}")
print(f"Shared secret (Bob): {bobSecret}")

# Simple encryption/decryption using shared secret x-coordinate
message = int(input("Enter message as number (0-16): "))
sharedX = aliceSecret[0]

cipher = (message + sharedX) % 17
print(f"Encrypted message (cipher): {cipher}")

decrypted = (cipher - sharedX) % 17
print(f"Decrypted message: {decrypted}")
