import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import matplotlib

#estilo de la grafica q se usara
matplotlib.style.use('ggplot')

#colores de las columnas que se usan
colors = ["#006D2C", "#31AAAA", "#74C476"]
#diccionario de datos necesarios para la grafica
data = {'Año':[2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019],
        'Mes':['Jan', 'Feb', 'Mar', 'Jan', 'Feb', 'Mar', 'Jan', 'Feb', 'Mar'],
        'Valor':[1, 2, 3, 4, 5, 6, 7, 8, 9]}

#insertamos los datos
pivot_df = pd.DataFrame(data)
#decimos la distribucion de columnas y filas
pivot_df = pd.pivot(pivot_df,index='Año', columns='Mes', values='Valor');

#indicamos que datos queremos mostrar y el tamaño colores.. de las columnas
pivot_df.loc[:,['Jan', 'Feb', 'Mar']].plot.bar(stacked=True, color=colors, figsize=(10,7));