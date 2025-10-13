s_box = [
    0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5,
    0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76
]

rcon = [0x01, 0x00, 0x00, 0x00]

def rotword(word):
    return word[1:] + word[:1]

def subword(word):
    return [s_box[b] for b in word]

def xorword(a, b):
    return [x ^ y for x, y in zip(a, b)]

def gfunction(word):
    return xorword(subword(rotword(word)), rcon)

key = []
for _ in range(4):
    key.append([int(x) for x in input().split()])

w0, w1, w2, w3 = key

g = gfunction(w3)
round1 = [
    xorword(w0, g),
    xorword(xorword(w0, g), w1),
    xorword(xorword(xorword(w0, g), w1), w2),
    xorword(xorword(xorword(xorword(w0, g), w1), w2), w3)
]

for word in round1:
    print(" ".join(f"{b:02x}" for b in word))
