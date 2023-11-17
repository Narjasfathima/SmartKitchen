from django.db import models
from Shop.models import ShopTb
# Create your models here.
class ProductTb(models.Model):
    pid = models.AutoField(primary_key=True)
    # sid = models.IntegerField()
    sid= models.ForeignKey(ShopTb, to_field='sid', on_delete=models.CASCADE)
    pname = models.CharField(max_length=50)
    ptype = models.CharField(max_length=50)
    pimage = models.CharField(max_length=50)
    pqty = models.CharField(max_length=25)
    price = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'product_tb'
