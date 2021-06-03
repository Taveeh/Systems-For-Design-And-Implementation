$purchases_endpoint = "http://purchases:8080/web-1.0-SNAPSHOT/api/purchases"
$headers = { "Accept" => "application/json", "Accept-Encoding" => "gzip, deflate", "Content-Type" => "application/json" }

class PurchasesController < ApplicationController
  skip_before_action :verify_authenticity_token

  def purchases
    result = RestClient.get $purchases_endpoint, $headers
    render json: { :purchases => JSON.parse(result.body)["purchases"] }
  end

  def add
    RestClient.post $purchases_endpoint, { catId: params[:catId], foodId: params[:foodId] }, $headers
  end

  def min_review
    result = RestClient.get $purchases_endpoint + "/minReview=" + params[:review], $headers
    render json: { :purchases => JSON.parse(result.body)["purchases"] }
  end

  def delete
    RestClient.delete $purchases_endpoint + "/" + params[:catId] + "&" + params[:customerId], $headers
  end
end
