from django.db import models

# Create your models here.
class KitchenBasketTb(models.Model):
    kid = models.AutoField(primary_key=True)
    name = models.CharField(max_length=25)
    recipie = models.CharField(max_length=1000)
    inid = models.CharField(max_length=50)
    ingredients = models.CharField(max_length=500, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'kitchen_basket_tb'

class IngredientTb(models.Model):
    inid = models.AutoField(primary_key=True)
    ingred = models.CharField(max_length=50)

    class Meta:
        managed = False
        db_table = 'ingredient_tb'



class Recipie(models.Model):
    nid = models.IntegerField()
    recipie = models.CharField(max_length=50)

    class Meta:
        managed = False
        db_table = 'recipie'



class Temp(models.Model):
    name = models.CharField(max_length=50)
    rec = models.CharField(max_length=1000)
    inid = models.CharField(max_length=110)

    class Meta:
        managed = False
        db_table = 'temp'


