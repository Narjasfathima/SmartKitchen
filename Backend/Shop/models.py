from django.db import models
from Home.models import LoginTb
# Create your models here.

class ShopTb(models.Model):
    sid = models.AutoField(primary_key=True)
    # lid = models.IntegerField()
    lid= models.ForeignKey(LoginTb, to_field='lid', on_delete=models.CASCADE)
    sname = models.CharField(max_length=25)
    splace = models.CharField(max_length=25)
    sphno = models.BigIntegerField()
    semail = models.CharField(max_length=25)
    status = models.CharField(max_length=25)

    class Meta:
        managed = False
        db_table = 'shop_tb'
