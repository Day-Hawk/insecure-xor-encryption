import random


class Pair:
    """
    A class to represent a pair of key and cypher.

    Attributes
    ----------
    key : list of int
        The key used for encryption and decryption.
    cypher : bytearray
        The encrypted message.
    """

    def __init__(self, key, cypher):
        """
        Constructs all the necessary attributes for the Pair object.

        Parameters
        ----------
        key : list of int
            The key used for encryption and decryption.
        cypher : bytearray
            The encrypted message.
        """
        self.key = key
        self.cypher = cypher


def encrypt(message):
    """
    Encrypts a message using a random key.

    Parameters
    ----------
    message : str
        The message to be encrypted.

    Returns
    -------
    Pair
        A Pair object containing the key and the cypher.
    """
    content = message.encode('utf-8')
    cypher = bytearray(len(content))
    key = [random.randint(0, 1) for _ in range(len(content))]

    for i in range(len(content)):
        cypher[i] = content[i] ^ key[i]

    return Pair(key, cypher)


def decrypt(pair):
    """
    Decrypts a cypher using the given key.

    Parameters
    ----------
    pair : Pair
        A Pair object containing the key and the cypher.

    Returns
    -------
    str
        The decrypted message.
    """
    message = bytearray(len(pair.cypher))

    for i in range(len(pair.cypher)):
        message[i] = pair.cypher[i] ^ pair.key[i]

    return message.decode('utf-8')


def main():
    """
    Main function to demonstrate encryption and decryption.
    """
    message = "Here is the text."

    print("Encrypting:", message)
    p = encrypt(message)

    print("Key:", p.key)
    print("Cypher:", list(p.cypher))
    print("-------------------------")
    print("Decrypted Message:", decrypt(p))


if __name__ == "__main__":
    main()
