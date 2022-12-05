import pandas as pd
import matplotlib

#estilo de la grafica q se usara
matplotlib.style.use('ggplot')

#colores de las columnas que se usan
colors = ["#F7520A", "#F70AC8", "#0AF7F7"]
#diccionario de datos necesarios para la grafica
data = {'Año':[2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019],
        'Mes':['Jan', 'Feb', 'Mar', 'Jan', 'Feb', 'Mar', 'Jan', 'Feb', 'Mar'],
        'Valor':[1, 2, 3, 4, 5, 6, 7, 8, 9]}

#insertamos los datos
pivot_df = pd.DataFrame(data)
#decimos la distribucion de columnas y filas
pivot_df = pd.pivot(pivot_df,index='Año', columns='Mes', values='Valor');

#indicamos que datos queremos mostrar en la leyenda ['Jan', 'Feb', 'Mar']
#Tamaño y colores de las columnas plot.bar(stacked=True, color=colors, figsize=(10,7))
pivot_df.loc[:,['Jan', 'Feb', 'Mar']].plot.bar(stacked=True, color=colors, figsize=(10,7));