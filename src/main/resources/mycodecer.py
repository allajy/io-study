
import uuid
import socket


s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.connect(("127.0.0.1",9001))
data = "1223213哈哈哈"
print(data)
dataBytes = bytes(data,"utf-8")
dataLen = len(dataBytes) 

data_len = dataLen.to_bytes(4, byteorder='big')
print(data_len)
s.sendall(data_len + dataBytes)

# for i in range(100):
#     print(i)
#     s.sendall(data_len + dataBytes)



