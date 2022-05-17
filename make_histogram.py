
import numpy as np
from matplotlib import pyplot as plt

#if cannot run in unix, should be run manully.
#will read from file data_for_histogram.txt and creat the histogram as Node_degree_histogram.png

with open("data_for_histogram.txt",'r') as h_in:
   raw_data = h_in.read()

print("Creating histogram...")
data = []
for sizes in raw_data.split(","):
    data.append(int(sizes))

bins = np.arange(0, max(data), 100) # fixed bin size

plt.xlim([min(data)-50, max(data)+50])

plt.hist(data, bins=bins, edgecolor='black')
plt.title('Node degree distribution')
plt.xlabel('Node degree (range = 100)')
plt.ylabel('Count')
plt.yscale("log")


plt.savefig('Node_degree_histogram.png')
#print(len(data))
print("The histogram is saved as Node_degree_histogram.png.")
plt.show()
