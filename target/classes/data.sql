Insert into category VALUES(1,'Çorbalar','img','Çorba')
Insert into category VALUES(2,'Pizza Çeşitleri','img','Pizza')
Insert into category VALUES(3,'Et Yemekleri','img','Et Yemeği')

Insert into product VALUES(1,'Pizza','Bol Zeytinli Pizza','Zeytinli Pizza',25,2)
Insert into product VALUES(2,'Pizza','Bol Sucuklu Pizza','Sucuklu Pizza',30,2)
Insert into product VALUES(3,'Pizza','Bol Malzelemi Pizza','Bol Malzemos Pizza',25,2)

Insert into product VALUES(4,'Çorba','Süzme Mercimek Çorbası','Mercimek Çorbası',10,1)
Insert into product VALUES(5,'Çorba','Ezo Mu Gelin?','Ezogelin Çorbası',12,1)
Insert into product VALUES(6,'Çorba','Yayladan Halka','Yayla Çorbası',8,1)
Insert into product VALUES(7,'Çorba','Üzeri Kaşarlı Domates Çorbası','Domates Çorbası',10,1)
Insert into product VALUES(8,'Çorba','Bolu Usulü Buldur Çorbası','Bulgur Çorbası',5,1)

Insert into product VALUES(9,'Et Yemeği','Bursa Üsülü İskender Kebap','İskender Kebap',40,3)
Insert into product VALUES(10,'Et Yemeği','Karnı Yarık','karnıYarık',25,3)

Insert into table_category VALUES(1,'Salon',10)
Insert into table_category VALUES(2,'Bahçe',5)
Insert into table_category VALUES(3,'Balkon',8)

Insert into users VALUES('omer',true,'{noop}1234')
Insert into authorities VALUES('omer','ROLE_USER')