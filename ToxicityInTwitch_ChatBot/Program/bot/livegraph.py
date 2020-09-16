import matplotlib.pyplot as plt
import matplotlib.animation as animation
from matplotlib import style

style.use('fivethirtyeight')

fig = plt.figure()
ax1 = fig.add_subplot(1,1,1)

#funció que ens llegeix el fitxer graph_data, amb el temps i l'index de toxicitat, i ens genera un plot 
def animate(i):
	graph_data = open('graph_data.txt','r').read()
	lines = graph_data.split('\n')
	xs = []
	ys = []
	for line in lines:
		if len(line) > 1:
			x,y = line.split(',')
			xs.append(float(x))
			ys.append(float(y))
			ax1.set_xlim(left=max(0, float(x)-50), right=float(x)+50)
	ax1.clear()
	ax1.plot(xs,ys)

ani = animation.FuncAnimation(fig, animate, 1000)
plt.show()

