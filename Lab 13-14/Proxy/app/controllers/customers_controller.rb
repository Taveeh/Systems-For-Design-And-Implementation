require 'rest-client'
require 'json'
$customers_endpoint = 'http://customers:8080/web-1.0-SNAPSHOT/api/customers'
$headers = { "Accept" => "application/json", "Accept-Encoding" => "gzip, deflate", "Content-Type" => "application/json" }

class CustomersController < ApplicationController
  skip_before_action :verify_authenticity_token

  def customers
    response = RestClient.get $customers_endpoint, $headers
    render json: { :customers => JSON.parse(response.body)["customers"] }
  end

  def add
    RestClient.post $customers_endpoint,
                    { name: params[:name], phoneNumber: params[:phoneNumber] },
                    $headers
  end

  def update
    RestClient.put $customers_endpoint + '/' + params[:id],
                   { name: params[:name], phoneNumber: params[:phoneNumber] },
                   $headers
  end

  def delete
    RestClient.delete $customers_endpoint + '/' + params[:id], $headers
  end
end
