package be.rubus.microstream.cloud.azure.demo;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.common.StorageSharedKeyCredential;
import one.microstream.afs.azure.storage.types.AzureStorageConnector;
import one.microstream.afs.blobstore.types.BlobStoreFileSystem;
import one.microstream.storage.embedded.configuration.types.EmbeddedStorageConfiguration;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;
import one.microstream.storage.types.StorageManager;

import java.util.Locale;

public class StorageManagerFactory {

    public static EmbeddedStorageManager defineStorageManagerProgrammatically(Root root) {
        BlobServiceClient client = createClient();
        BlobStoreFileSystem fileSystem = BlobStoreFileSystem.New(
                AzureStorageConnector.Caching(client)
        );

        // microstream-storage is the name of the (existing) container within your storage
        return EmbeddedStorage.start(root, fileSystem.ensureDirectoryPath("microstream-storage"));
    }

    private static BlobServiceClient createClient() {
        /*
         * From the Azure portal, get your Storage account's name and account key.
         */
        String accountName = "<use your account name>";
        String accountKey = "<use your account key>";

        /*
         * Use your Storage account's name and key to create a credential object; this is used to access your account.
         */
        StorageSharedKeyCredential credential = new StorageSharedKeyCredential(accountName, accountKey);

        /*
         * From the Azure portal, get your Storage account blob service URL endpoint.
         * The URL typically looks like this:
         */
        String endpoint = String.format(Locale.ROOT, "https://%s.blob.core.windows.net", accountName);

        /*
         * Create a BlobServiceClient object that wraps the service endpoint, credential and a request pipeline.
         */
        return new BlobServiceClientBuilder().endpoint(endpoint).credential(credential).buildClient();

    }

    public static StorageManager defineStorageManagerDeclarative(Root root) {
        return EmbeddedStorageConfiguration.load(
                        "storage.properties"
                )
                .createEmbeddedStorageFoundation()
                .createEmbeddedStorageManager(root)
                .start();
    }
}
