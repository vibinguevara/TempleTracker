var temple_name = msg['row']['column1'].toString().replace(/^\d+\s+\d+\s+/, '');
channelMap.put('temple_name',temple_name);

var other_address = msg['row']['column2'].toString();
channelMap.put('other_address',other_address);

var city_address = msg['row']['column3'].toString().split('-')[0].trim();
channelMap.put('city_address',city_address);

var state_address = 'TamilNadu'
channelMap.put('state_address',state_address);

var temple_postalcode = msg['row']['column4'].toString().trim().split(' ')[0];
channelMap.put('temple_postalcode',temple_postalcode.toString());

var temple_juridiction = msg['row']['column4'].toString().trim().replace(/^\d{6}\s+/, '');
channelMap.put('temple_juridiction',temple_juridiction.toString());