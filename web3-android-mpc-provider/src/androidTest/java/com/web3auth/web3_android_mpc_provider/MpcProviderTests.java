package com.web3auth.web3_android_mpc_provider;

import static org.junit.Assert.assertNotNull;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigInteger;

@RunWith(AndroidJUnit4.class)
public class MpcProviderTests {

    static {
        System.loadLibrary("dkls-native");
    }

    final String example1 = "{\"types\":{\"EIP712Domain\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"version\",\"type\":\"string\"},{\"name\":\"chainId\",\"type\":\"uint256\"},{\"name\":\"verifyingContract\",\"type\":\"address\"}],\"Person\":[{\"name\":\"name\",\"type\":\"string\"},{{\"name\":\"wallet\",\"type\":\"address\"}]],\"Mail\":[{\"name\":\"from\",\"type\":\"Person\"},{\"name\":\"to\",\"type\":\"Person\"},{\"name\":\"contents\",\"type\":\"string\"}]},\"primaryType\":\"Mail\",\"domain\":{\"name\":\"Ether Mail\",\"version\":\"1\",\"chainId\":1,\"verifyingContract\":\"0xCcCCccccCCCCcCCCCCCcCcCccCcCCCcCcccccccC\"},\"message\":{\"from\":{\"name\":\"Account\",\"wallet\":\"0x048975d4997d7578a3419851639c10318db430b6\"},\"to\":{\"name\":\"Bob\",\"wallet\":\"0xbBbBBBBbbBBBbbbBbbBbbbbBBbBbbbbBbBbbBBbB\"},\"contents\":\"Hello, Bob!\"}}";

    final String fullAddress = "04238569d5e12caf57d34fb5b2a0679c7775b5f61fd18cd69db9cc600a651749c3ec13a9367380b7a024a67f5e663f3afd40175c3223da63f6024b05d0bd9f292e";
    final String factorKey = "3b4af35bc4838471f94825f34c4f649904a258c0907d348bed653eb0c94ec6c0";
    final int tssNonce = 0;
    final String tssShare = "4f62ddd962fab8b0777bd18a2e6f3992c7e15ff929df79a15a7046da46af5a05";
    final String tssIndex = "2";
    final String selected_tag = "default";
    final String verifier = "google-lrc";
    final String verifierId = "hqjang95@gmail.com";

    String[] tssEndpoints = {"https://sapphire-1.auth.network/tss",
            "https://sapphire-2.auth.network/tss",
            "https://sapphire-3.auth.network/tss",
            "https://sapphire-4.auth.network/tss",
            "https://sapphire-5.auth.network/tss"};

    String[] sigs = {
            "{\"sig\":\"16de7c5812aedf492e7afe4a9c0607dba6d8d908d30ef1eb2e4761bc300bb3fc62bfbd0e94b03aa5eb496b5ed7adfa4203fa9745d90673cf789d3a989f872ae41b\",\"data\":\"eyJleHAiOjE2OTM0NjYxMTAsInRlbXBfa2V5X3giOiI2MTg3NTM3ZTc1YThhNWQ3NWQzZjhkMGZmYzE4NjMwNTRjYjEzNmE3YzRjYWVjNWRkYjUyZjViNmY1MTcyZDEwIiwidGVtcF9rZXlfeSI6ImFhNTNhNmE2N2YzOTE1NzNmYTA1YTVkZWViZjM2MDVkM2MzODljNjhjMDhlOGI5YzllNDQyODU1ZWYyYWE2ZTkiLCJ2ZXJpZmllcl9uYW1lIjoiZ29vZ2xlLWxyYyIsInZlcmlmaWVyX2lkIjoiaHFqYW5nOTVAZ21haWwuY29tIiwic2NvcGUiOiIifQ==\"}",
            "{\"sig\":\"50a7451f2a8af5f3e193b3e53768e3107f8d606ef5e9ee70aba15fba8e67a1be279d71f8d3b6a954beef5e5119a10195c3017e48b3f0a93b557ed9366ce38f171c\",\"data\":\"eyJleHAiOjE2OTM0NjYxMTAsInRlbXBfa2V5X3giOiI2MTg3NTM3ZTc1YThhNWQ3NWQzZjhkMGZmYzE4NjMwNTRjYjEzNmE3YzRjYWVjNWRkYjUyZjViNmY1MTcyZDEwIiwidGVtcF9rZXlfeSI6ImFhNTNhNmE2N2YzOTE1NzNmYTA1YTVkZWViZjM2MDVkM2MzODljNjhjMDhlOGI5YzllNDQyODU1ZWYyYWE2ZTkiLCJ2ZXJpZmllcl9uYW1lIjoiZ29vZ2xlLWxyYyIsInZlcmlmaWVyX2lkIjoiaHFqYW5nOTVAZ21haWwuY29tIiwic2NvcGUiOiIifQ==\"}",
            "{\"sig\":\"d94979a0f743a8a41630167622c5b443b148f231bb2293e60a17ab4ea7ebdf38713b81b0bc9161ecd3949ddcf8cfca9734f136ba02c2e4e670fb4b8523299ab01b\",\"data\":\"eyJleHAiOjE2OTM0NjYxMTAsInRlbXBfa2V5X3giOiI2MTg3NTM3ZTc1YThhNWQ3NWQzZjhkMGZmYzE4NjMwNTRjYjEzNmE3YzRjYWVjNWRkYjUyZjViNmY1MTcyZDEwIiwidGVtcF9rZXlfeSI6ImFhNTNhNmE2N2YzOTE1NzNmYTA1YTVkZWViZjM2MDVkM2MzODljNjhjMDhlOGI5YzllNDQyODU1ZWYyYWE2ZTkiLCJ2ZXJpZmllcl9uYW1lIjoiZ29vZ2xlLWxyYyIsInZlcmlmaWVyX2lkIjoiaHFqYW5nOTVAZ21haWwuY29tIiwic2NvcGUiOiIifQ==\"}"
    };

    BigInteger[] nodeIndexs = {new BigInteger("1"), new BigInteger("2"), new BigInteger("3")};

    @Test
    public void testSigningMessage() {
        EthTssAccountParams params = new EthTssAccountParams(
                fullAddress, factorKey, tssNonce, tssShare, tssIndex, selected_tag, verifier, verifierId,
                nodeIndexs, tssEndpoints, sigs);

        EthereumTssAccount account = new EthereumTssAccount(params);

        String msg = "hello world";
        String signature = account.signMessage(msg);
        assertNotNull(signature);
    }

    @Test
    public void testSigningTransaction() {
        EthTssAccountParams params = new EthTssAccountParams(
                fullAddress, factorKey, tssNonce, tssShare, tssIndex, selected_tag, verifier, verifierId,
                nodeIndexs, tssEndpoints, sigs);

        EthereumTssAccount account = new EthereumTssAccount(params);

        String fromAddress = Utils.generateAddressFromPubKey(params.getPublicKey());
        System.out.println("fromAddress: " + fromAddress);
        String toAddress = "0x048975d4997D7578A3419851639c10318db430b6"; //Utils.generateAddressFromPubKey(params.getPublicKey());
        String transactionHash = account.signAndSendTransaction("https://rpc.ankr.com/eth_goerli", 0.001,
                fromAddress, toAddress);
        System.out.println("Transaction Hash: " + transactionHash);
        assertNotNull(transactionHash);
    }

}