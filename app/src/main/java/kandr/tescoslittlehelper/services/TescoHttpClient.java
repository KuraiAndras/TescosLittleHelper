package kandr.tescoslittlehelper.services;

import kandr.tescoslittlehelper.data.ProductData;
import kandr.tescoslittlehelper.helpers.DbHelper;

public class TescoHttpClient implements TescoProductApi {
    @Override
    public ProductData getProductData(String gtin) {
        //TODO: make http request, if fails search in local db
//        HttpClient httpclient = HttpClients.createDefault();
//
//        try
//        {
//            URIBuilder builder = new URIBuilder("https://dev.tescolabs.com/product/");
//
//            builder.setParameter("gtin", "{string}");
//            builder.setParameter("tpnb", "{string}");
//            builder.setParameter("tpnc", "{string}");
//            builder.setParameter("catid", "{string}");
//
//            URI uri = builder.build();
//            HttpGet request = new HttpGet(uri);
//            request.setHeader("Ocp-Apim-Subscription-Key", "{subscription key}");
//
//
//            // Request body
//            StringEntity reqEntity = new StringEntity("{body}");
//            request.setEntity(reqEntity);
//
//            HttpResponse response = httpclient.execute(request);
//            HttpEntity entity = response.getEntity();
//
//            if (entity != null)
//            {
//                System.out.println(EntityUtils.toString(entity));
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//        }
        return DbHelper.getMockedProductData(gtin);
    }
}
